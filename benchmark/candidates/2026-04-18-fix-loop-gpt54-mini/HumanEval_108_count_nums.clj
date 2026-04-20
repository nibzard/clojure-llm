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
            (let [s (str n)]
              (reduce + (map-indexed (fn [i ch]
                                       (if (= ch \-)
                                         0
                                         (let [d (- (int ch) (int \0))]
                                           (if (and (neg? n) (= i 1))
                                             (- d)
                                             d))))
                                     s))))]
    (count (filter pos? (map digit-sum arr)))))