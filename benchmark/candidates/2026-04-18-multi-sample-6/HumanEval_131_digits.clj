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
  (let [odd-digits (->> (str n)
                        (map #(Character/digit ^char % 10))
                        (filter odd?))]
    (if (seq odd-digits)
      (reduce * odd-digits)
      0)))