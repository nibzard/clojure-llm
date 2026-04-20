(defn loss_amount
  "	Write a function that gives loss amount on a sale if the given amount has loss else return 0."
  [actual_cost sale_amount]
  (max 0 (- actual_cost sale_amount)))