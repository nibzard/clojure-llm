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
    (cond
      (zero? m) 0
      (> m n) 0
      :else
      (count
       (filter true?
               (map #(.startsWith ^String s ^String sub %)
                    (range (inc (- n m)))))))))