(defn count_occurance
  "	Write a function to count the number of occurence of the string 'std' in a given string."
  [s]
  (let [pattern "std"
        n (count pattern)]
    (if (or (nil? s) (zero? n) (nil? (seq s)) (< (count s) n))
      0
      (count (filter true?
                     (map #(= pattern (subs s % (+ % n)))
                          (range 0 (- (count s) n 1))))))))