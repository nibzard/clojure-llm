(defn even_odd_count
  "Given an integer. return a vector that has the number of even and odd digits respectively.
  Example:
      >>> (even_odd_count -12)
      [1 1]
      >>> (even_odd_count 123)
      [1 2]"
  [num]
  (let [digits (if (neg? num)
                 (rest (str num))
                 (str num))]
    (reduce (fn [[even odd] d]
              (let [n (Character/digit ^char d 10)]
                (if (even? n)
                  [(inc even) odd]
                  [even (inc odd)])))
            [0 0]
            digits)))