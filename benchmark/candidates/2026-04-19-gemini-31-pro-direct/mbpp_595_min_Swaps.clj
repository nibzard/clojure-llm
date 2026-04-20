(defn min_Swaps [str1 str2]
  (let [pairs (map vector str1 str2)
        m01 (count (filter #(= % [\0 \1]) pairs))
        m10 (count (filter #(= % [\1 \0]) pairs))]
    (if (= m01 m10)
      m01
      "Not Possible")))