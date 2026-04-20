(defn intersection [[s1 e1] [s2 e2]]
  (let [start (max s1 s2)
        end (min e1 e2)
        len (- end start)]
    (if (and (>= len 2)
             (not-any? #(zero? (mod len %)) (range 2 (inc (int (Math/sqrt len))))))
      "YES"
      "NO")))