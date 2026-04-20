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
  (if (empty? arr)
    0
    (count
      (filter
        (fn [n]
          (let [abs-n (Math/abs n)
                first-digit (quot abs-n (int (Math/pow 10 (int (Math/log10 abs-n)))))
                sum-digits (reduce + (map #(Character/digit % 10) (str abs-n)))]
            (> (+ sum-digits (- first-digit) first-digit) 0)))
        arr))))