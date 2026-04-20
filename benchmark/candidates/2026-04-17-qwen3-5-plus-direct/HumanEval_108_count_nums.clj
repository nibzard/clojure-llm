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
  (letfn [(sum-signed-digits [n]
            (if (zero? n)
              0
              (let [abs-n (Math/abs n)
                    digits (map #(Character/getNumericValue %) (str abs-n))]
                (if (neg? n)
                  (+ (- (first digits)) (reduce + (rest digits)))
                  (reduce + digits)))))]
    (count (filter #(> (sum-signed-digits %) 0) arr))))