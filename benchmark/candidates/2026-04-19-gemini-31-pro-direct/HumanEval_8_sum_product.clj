(defn sum_product
  [numbers]
  [(reduce + 0 numbers) (reduce * 1 numbers)])