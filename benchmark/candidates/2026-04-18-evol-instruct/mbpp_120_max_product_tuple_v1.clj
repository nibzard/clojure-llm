(defn max-pair-sum
  "Return the maximum sum of any two consecutive numbers in a sequence.

  Works with vectors, lists, and lazy/infinite sequences.

  Examples:
  (max-pair-sum [1 5 2 4])
  ;; => 7

  (max-pair-sum (range))
  ;; => 1"
  [nums]
  (let [pairs (partition 2 1 nums)]
    (reduce max (map #(apply + %) pairs))))