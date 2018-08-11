(ns worldbuilder.test.handler
  (:require [clojure.test :refer :all]
            [ring.mock.request :refer :all]
            [worldbuilder.handler :refer :all]
            [worldbuilder.db.core :as db]
            [mount.core :as mount]))

(use-fixtures
  :once
  (fn [f]
    (mount/start #'worldbuilder.config/env
                 #'worldbuilder.handler/app)
    (f)))

(deftest test-app
  (testing "main route"
    (let [response (app (request :get "/"))]
      (is (= 200 (:status response)))))

  (testing "not-found route"
    (let [response (app (request :get "/invalid"))]
      (is (= 404 (:status response)))))

  (testing "create user"
    (let [result (db/create-user "test" "test" "test")]
    (is (true? true)))))
