(ns worldbuilder.test.handler
  (:require [clojure.test :refer :all]
            [ring.mock.request :refer :all]
            [mount.core :refer :all]
            [monger.core :as mg]
            [monger.collection :as mc]
            [monger.operators :refer :all]
            [worldbuilder.handler :refer :all]
            [worldbuilder.config :refer [env]]
            [worldbuilder.db.core :as db-query]))
; NOTE All tests assume a new user create with the username of 'testUser' - we create and run a series of tests on this

; TODO we probably shouldn't do this here - move the teardown DB calls to another file maybe?
(defstate db*
  :start (-> env :database-url mg/connect-via-uri)
  :stop (-> db* :conn mg/disconnect))

(defstate db
  :start (:db db*))


(defn start-states [f]
  (mount.core/start)
  (f)
  (mc/remove db "user" { :username "testUser" }))
;
(clojure.test/use-fixtures :once start-states)


(deftest test-app

  (testing "Should create a new user"
    (db-query/create-user "testUser" "test" "test")
    (is (= 1 (db-query/get-user-count-username "testUser"))))

  (testing "Should update the token on the user"
    (let [current-user (db-query/get-user-by-username "testUser")
          prev-token (:token current-user)]
          (is (not= prev-token (db-query/generate-new-token "testUser")))))


  (testing "Should create a world for a user"
    (let [response (app  (-> (request :post "/api/worlds")
                             (body :name "testUser-world")
                             (header :token "1b96fb2b-fb33-4b3b-84cb-1c8c08747d1e")
    ))]
    (is (= 401 (:status response))))))
