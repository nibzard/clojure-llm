(defn lcs_of_three
  "Write a function to find the longest common subsequence for the given three string sequence."
  [X Y Z]
  (let [m (count X)
        n (count Y)
        o (count Z)
        ;; Create a 3D memoization map initialized with 0 for the base case (nil keys)
        ;; We use a map with keys [i j k] to store the length of LCS for
        ;; X[0..i-1], Y[0..j-1], Z[0..k-1]
        memo (atom {nil 0})]
    
    (letfn [(solve [i j k]
              (or (get @memo [i j k])
                  (let [res (cond
                              ;; If any string is exhausted, LCS is 0
                              (or (zero? i) (zero? j) (zero? k)) 0
                              
                              ;; If characters match, add 1 to the result of the rest
                              (= (nth X (dec i)) (nth Y (dec j)) (nth Z (dec k)))
                              (inc (solve (dec i) (dec j) (dec k)))
                              
                              ;; If characters don't match, try all 3 possibilities of skipping a char
                              :else
                              (max (solve (dec i) j k)
                                   (solve i (dec j) k)
                                   (solve i j (dec k))))]
                    (swap! memo assoc [i j k] res)
                    res)))]
      (solve m n o))))