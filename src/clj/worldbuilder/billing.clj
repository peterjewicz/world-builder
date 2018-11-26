(ns worldbuilder.billing
  (:require [clj-stripe.util :as util]
  	  [clj-stripe.common :as common]
  	  [clj-stripe.plans :as plans]
  	  [clj-stripe.coupons :as coupons]
  	  [clj-stripe.charges :as charges]
  	  [clj-stripe.cards :as cards]
  	  [clj-stripe.subscriptions :as subscriptions]
  	  [clj-stripe.customers :as customers]
  	  [clj-stripe.invoices :as invoices]
  	  [clj-stripe.invoiceitems :as invoiceitems]
      [clj-http.client :as client]
      [worldbuilder.db.core :as db]
      [worldbuilder.config :refer [env]]))


(defn get-stripe-plans []
  (common/with-token (:stripe-private-key env)
    (common/execute (plans/get-all-plans))))

; TODO get user email here
(defn create-new-customer [stripeToken email]
  (common/with-token (:stripe-private-key env)
    (common/execute (customers/create-customer
      (common/card (:id stripeToken))
      (customers/email email)
      (common/plan (:stripe-plan env))))))

(defn unsubscribe-user
  "Unsubscribes a user from stripe"
  [subToken]
  (let [url (str "https://api.stripe.com/v1/subscriptions/" subToken)]
    (client/delete url {:basic-auth "sk_test_AVbHuiy5t5NO427aI4czG67e:"})))
