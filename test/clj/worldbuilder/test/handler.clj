(ns worldbuilder.test.handler
  (:require [clojure.test :refer :all]
            [ring.mock.request :refer :all]
            [mount.core :refer :all]
            [monger.core :as mg]
            [monger.collection :as mc]
            [monger.operators :refer :all]
            [worldbuilder.handler :refer :all]
            [worldbuilder.db.core :as db-query]))





(defn start-states [f]
  (mount.core/start)
  (f))
;
(clojure.test/use-fixtures :once start-states)


(deftest test-app

  (testing "main route"
    (is (= 2 2)))

  (testing "create user"
    (db-query/create-user "test1111" "test" "test")
    (is (true? true))))
