(defn count_nums
  "Write a function count_nums which takes a vector of integers and returns
  the number of elements which has a sum of digits > 0.
  If a number is negative, then its first signed digit will be negative:
  e.g. -123 has signed digits -1, 2, and 3."
  [arr]
  (let [sum-digits (fn [n]
                     (let [s (str n)]
                       (if (= \- (first s))
                         (let [digits (map #(Character/digit % 10) (rest s))]
                           (apply + (- (first digits)) (rest digits)))
                         (apply + (map #(Character/digit % 10) s)))))]
    (count (filter #(> (sum-digits %) 0) arr))))