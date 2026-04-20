(defn count_nums
  "Write a function count_nums which takes a vector of integers and returns
  the number of elements which has a sum of digits > 0.
  If a number is negative, then its first signed digit will be negative:
  e.g. -123 has signed digits -1, 2, and 3.
  >>> (count_nums [])
  0
  >>> (count_nums [-1 11 -11])
  1
  >>> (count_nums [1 1 2])
  3"
  [arr]
  (letfn [(digit-sum [n]
            (if (neg? n)
              (let [digits (map #(- (Character/digit ^char % 10)) (str (- (first (str n)) 0)))
                    s (str (- n))
                    first-digit (- (Character/digit ^char (first s) 10))
                    rest-digits (map #(Character/digit ^char % 10) (rest s))]
                (+ first-digit (reduce + 0 rest-digits)))
              (reduce + 0 (map #(Character/digit ^char % 10) (str n)))))]
    (count (filter #(pos? (digit-sum %)) (or arr [])))))