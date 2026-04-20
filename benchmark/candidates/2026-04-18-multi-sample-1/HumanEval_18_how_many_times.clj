(defn how_many_times
  " Find how many times a given substring can be found in the original string. Count overlaping cases.
  >>> (how_many_times \"\" \"a\")
  0
  >>> (how_many_times \"aaa\" \"a\")
  3
  >>> (how_many_times \"aaaa\" \"aa\")
  3"
  [string substring]
  (let [s (or string "")
        sub (or substring "")
        n (.length ^String s)
        m (.length ^String sub)]
    (if (or (zero? n) (zero? m) (> m n))
      0
      (count
       (filter true?
               (map #(= sub (subs s % (+ % m)))
                    (range (inc (- n m)))))))))