(defn even_odd_count
  "Given an integer. return a vector that has the number of even and odd digits respectively.
  Example:
      >>> (even_odd_count -12)
      [1 1]
      >>> (even_odd_count 123)
      [1 2]"
  [num]
  (let [digits (map #(Character/digit ^char % 10) (str (Math/abs (long num))))]
    [(count (filter even? digits))
     (count (filter odd? digits))]))