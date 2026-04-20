(defn even_odd_count
  "Given an integer. return a vector that has the number of even and odd digits respectively.
  Example:
      >>> (even_odd_count -12)
      [1 1]
      >>> (even_odd_count 123)
      [1 2]"
  [num]
  (let [s (str (Math/abs (long num)))]
    (reduce (fn [[even odd] ch]
              (let [digit (Character/digit ^char ch 10)]
                (if (even? digit)
                  [(inc even) odd]
                  [even (inc odd)])))
            [0 0]
            s)))