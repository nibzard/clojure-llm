(defn lcs_of_three
  "	Write a function to find the longest common subsequence for the given three string sequence. https://www.geeksforgeeks.org/lcs-longest-common-subsequence-three-strings/"
  [X Y Z]
  (let [x (or X "")
        y (or Y "")
        z (or Z "")
        m (count x)
        n (count y)
        o (count z)
        memo (atom {})]
    (letfn [(lcs-len [i j k]
              (if (or (zero? i) (zero? j) (zero? k))
                0
                (if-let [v (get @memo [i j k])]
                  v
                  (let [v (if (= (nth x (dec i)) (nth y (dec j)) (nth z (dec k)))
                            (inc (lcs-len (dec i) (dec j) (dec k)))
                            (max (lcs-len (dec i) j k)
                                 (lcs-len i (dec j) k)
                                 (lcs-len i j (dec k))))]
                    (swap! memo assoc [i j k] v)
                    v))))
            (build-lcs [i j k]
              (cond
                (or (zero? i) (zero? j) (zero? k)) ""
                (= (nth x (dec i)) (nth y (dec j)) (nth z (dec k)))
                (str (build-lcs (dec i) (dec j) (dec k))
                     (nth x (dec i)))
                :else
                (let [a (lcs-len (dec i) j k)
                      b (lcs-len i (dec j) k)
                      c (lcs-len i j (dec k))]
                  (cond
                    (and (>= a b) (>= a c)) (build-lcs (dec i) j k)
                    (>= b c) (build-lcs i (dec j) k)
                    :else (build-lcs i j (dec k))))))]
      (build-lcs m n o))))