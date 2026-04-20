(defn order_by_points
  "Write a function which sorts the given list of integers
  in ascending order according to the sum of their digits.
  Note: if there are several items with similar sum of their digits,
  order them based on their index in original list."
  [nums]
  (let [sum-digits (fn [n]
                     (let [s (str n)
                           digits (map #(Character/digit % 10) 
                                       (if (neg? n) (subs s 1) s))]
                       (if (neg? n)
                         (apply + (- (first digits)) (rest digits))
                         (apply + digits))))]
    (vec (sort-by sum-digits nums))))