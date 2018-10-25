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
      [worldbuilder.config :refer [env]]))


(defn get-stripe-plans []
  (common/with-token (:stripe-private-key env)
    (common/execute (plans/get-all-plans))))

; TODO get user email here
(defn create-new-customer [stripeToken]
  (println (:id stripeToken))
  (common/with-token (:stripe-private-key env)
    (common/execute (customers/create-customer
      (common/card (:id stripeToken))
      (customers/email "worldbuider@stripe.com")
      (common/plan "plan_DhsxzAIntQRzqu")))))

; TODO see how SLT handles this
(defn unsubscribe-user
  "Unsubscribes a user from stripe"
  [])
