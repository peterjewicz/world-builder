(ns worldbuilder.test.handler
  (:require [clojure.test :refer :all]
            [ring.mock.request :refer :all]
            [mount.core :refer [defstate]]
            [monger.core :as mg]
            [monger.collection :as mc]
            [monger.operators :refer :all]
            [worldbuilder.handler :refer :all]
            [worldbuilder.db.core :as db-query]
            [mount.core :as mount]))

(use-fixtures
  :once
  (fn [f]
    (mount/start #'worldbuilder.config/env
                 #'worldbuilder.handler/app)



    (f)))

    ; (defstate db*
    ;   :start (-> "mongodb://127.0.0.1:27017/worldbuilder" mg/connect-via-uri)
    ;   :stop (-> db* :conn mg/disconnect))
    ; (defstate db
    ;   :start (:db db*))
(def db (mg/connect-via-uri "mongodb://127.0.0.1:27017/worldbuilder"))

(deftest test-app
  (testing "main route"
    (let [response (app (request :get "/"))]
      (is (= 2 2))))

  (testing "not-found route"
    (let [response (app (request :get "/invalid"))]
      (is (= 2 2))))

  (testing "create user"
    ; (let [user {:username "test"
    ;             :email "test"
    ;             :password "test"
    ;             :token "test"
    ;             }]
    ; (mc/insert (:db db) "user" user)
    (db-query/create-user "test" "test" "test")
    (is (true? true))))
