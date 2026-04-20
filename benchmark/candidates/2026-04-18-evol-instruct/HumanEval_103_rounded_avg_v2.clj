(defn rounded_avg
  "You are given a vector of integers. Compute the average of the elements,
  round it to the nearest integer, and convert that result to a binary string
  prefixed with \"0b\".

  If the vector is empty, return -1.
  If any element is nil, return -1.

  Examples:
  >>> (rounded_avg [1 5])
  \"0b11\"
  >>> (rounded_avg [10 20 33])
  \"0b11010\"
  >>> (rounded_avg [])
  -1
  >>> (rounded_avg [2 nil 4])
  -1"
  [xs]
  (if (or (empty? xs) (some nil? xs))
    -1
    (let [avg (Math/round (double (/ (reduce + xs) (count xs))))]
      (str "0b" (Long/toBinaryString avg)))))