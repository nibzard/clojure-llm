(defn sort_array
  "In this Kata, you have to sort a vector of non-negative integers according to
  number of ones in their binary representation in ascending order.
  For similar number of ones, sort based on decimal value.
  It must be implemented like this:
  >>> (sort_array [1 5 2 3 4])
  [1 2 3 4 5]
  >>> (sort_array [-2 -3 -4 -5 -6])
  [-6 -5 -4 -3 -2]
  >>> (sort_array [1 0 2 3 4])
  [0 1 2 3 4]"
  [arr]
  (let [bit-count (fn [n]
                    (loop [x (if (neg? n) (- n) n)
                           cnt 0]
                      (if (zero? x)
                        cnt
                        (recur (bit-and x (dec x)) (inc cnt)))))]
    (vec (sort-by (fn [n] [(bit-count n) n]) arr))))