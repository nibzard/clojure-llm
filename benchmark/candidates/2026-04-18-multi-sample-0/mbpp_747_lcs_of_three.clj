(defn lcs_of_three
  "	Write a function to find the longest common subsequence for the given three string sequence. https://www.geeksforgeeks.org/lcs-longest-common-subsequence-three-strings/"
  [X Y Z]
  (let [X (or X "")
        Y (or Y "")
        Z (or Z "")
        m (count X)
        n (count Y)
        o (count Z)]
    (cond
      (or (zero? m) (zero? n) (zero? o)) ""
      :else
      (let [memo (atom {})]
        (letfn [(lcs [i j k]
                  (if (or (zero? i) (zero? j) (zero? k))
                    ""
                    (if-let [cached (get @memo [i j k])]
                      cached
                      (let [result (if (= (nth X (dec i)) (nth Y (dec j)) (nth Z (dec k)))
                                     (str (lcs (dec i) (dec j) (dec k))
                                          (nth X (dec i)))
                                     (let [a (lcs (dec i) j k)
                                           b (lcs i (dec j) k)
                                           c (lcs i j (dec k))]
                                       (max-key count a b c)))]
                        (swap! memo assoc [i j k] result)
                        result))))]
          (lcs m n o)))))