(defn sum_Of_Subarray_Prod
  "Calculates the sum of products of all possible sublists of a given list.
   Uses a dynamic programming approach where dp[i] represents the sum of
   products of all sublists ending at index i."
  [arr]
  (if (empty? arr)
    0
    (loop [xs (rest arr)
           prev-val (first arr)
           total-sum (first arr)]
      (if (empty? xs)
        total-sum
        (let [curr-val (first xs)
              ;; Sum of products of sublists ending at current element:
              ;; curr-val + (curr-val * prev-val)
              new-prev (+ curr-val (* curr-val prev-val))
              new-total (+ total-sum new-prev)]
          (recur (rest xs) new-prev new-total))))))