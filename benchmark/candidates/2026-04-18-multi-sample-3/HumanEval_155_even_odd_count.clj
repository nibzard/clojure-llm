(defn even_odd_count
  "Given an integer. return a vector that has the number of even and odd digits respectively.
  Example:
      >>> (even_odd_count -12)
      [1 1]
      >>> (even_odd_count 123)
      [1 2]"
  [num]
  (reduce
   (fn [[even-count odd-count] ch]
     (let [digit (- (int ch) (int \0))]
       (if (even? digit)
         [(inc even-count) odd-count]
         [even-count (inc odd-count)])))
   [0 0]
   (str (if (neg? num) (- num) num))))