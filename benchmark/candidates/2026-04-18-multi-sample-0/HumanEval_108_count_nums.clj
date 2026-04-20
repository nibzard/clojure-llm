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
              (- (reduce + (map #(Character/digit ^char % 10) (str (- n))))
                 (* 2 (Character/digit ^char (first (str (- n))) 10)))
              (reduce + (map #(Character/digit ^char % 10) (str n)))))]
    (count (filter #(pos? (digit-sum %)) (or arr [])))))