(defn how_many_times
  " Find how many times a given substring can be found in the original string. Count overlaping cases.
  >>> (how_many_times \"\" \"a\")
  0
  >>> (how_many_times \"aaa\" \"a\")
  3
  >>> (how_many_times \"aaaa\" \"aa\")
  3"
  [string substring]
  (let [n (count substring)]
    (if (zero? n)
      0
      (count
       (filter true?
               (map #(= substring (subs string % (+ % n)))
                    (range 0 (inc (- (count string) n)))))))))