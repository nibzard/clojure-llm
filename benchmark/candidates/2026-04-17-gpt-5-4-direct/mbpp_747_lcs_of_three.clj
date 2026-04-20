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
    (letfn [(lcs [i j k]
              (if (or (zero? i) (zero? j) (zero? k))
                ""
                (if-let [cached (get @memo [i j k])]
                  cached
                  (let [res (if (= (nth x (dec i))
                                   (nth y (dec j))
                                   (nth z (dec k)))
                              (str (lcs (dec i) (dec j) (dec k))
                                   (nth x (dec i)))
                              (let [a (lcs (dec i) j k)
                                    b (lcs i (dec j) k)
                                    c (lcs i j (dec k))]
                                (max-key count a b c)))]
                    (swap! memo assoc [i j k] res)
                    res))))]
      (lcs m n o))))