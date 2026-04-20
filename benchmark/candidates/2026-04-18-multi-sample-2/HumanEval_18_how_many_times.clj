(defn how_many_times
  " Find how many times a given substring can be found in the original string. Count overlaping cases.
  >>> (how_many_times "" "a")
  0
  >>> (how_many_times "aaa" "a")
  3
  >>> (how_many_times "aaaa" "aa")
  3"
  [string substring]
  (let [s (or string "")
        sub (or substring "")
        sub-len (count sub)
        s-len (count s)]
    (if (or (zero? sub-len) (> sub-len s-len))
      0
      (count
       (filter #(= sub (subs s % (+ % sub-len)))
               (range 0 (inc (- s-len sub-len))))))))