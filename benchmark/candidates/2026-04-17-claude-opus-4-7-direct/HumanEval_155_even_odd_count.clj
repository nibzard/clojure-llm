(defn even_odd_count
  "Given an integer. return a vector that has the number of even and odd digits respectively.
  Example:
      >>> (even_odd_count -12)
      [1 1]
      >>> (even_odd_count 123)
      [1 2]"
  [num]
  (let [digits (str (Math/abs num))
        evens (count (filter #(Character/isDigit ^char %) (filter #(even? (Character/digit ^char % 10)) digits)))
        odds  (- (count digits) evens)]
    [evens odds]))