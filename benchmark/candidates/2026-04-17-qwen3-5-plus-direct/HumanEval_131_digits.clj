(defn digits
  "Given a positive integer n, return the product of the odd digits.
  Return 0 if all digits are even.
  For example:
  >>> (digits 1)
  1
  >>> (digits 4)
  0
  >>> (digits 235)
  15"
  [n]
  (let [odd-digits (->> n
                        str
                        (map #(Character/digit % 10))
                        (filter odd?))]
    (if (empty? odd-digits)
      0
      (reduce * odd-digits))))