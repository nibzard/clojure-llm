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
      (zero? n) 0
      (zero? m) 0
      (> m n) 0
      :else
      (loop [i 0
             cnt 0]
        (if (> i (- n m))
          cnt
          (recur (inc i)
                 (if (= sub (.substring ^String s i (+ i m)))
                   (inc cnt)
                   cnt)))))))