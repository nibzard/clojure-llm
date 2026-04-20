(defn bf
  [planet1 planet2]
  (let [planets ["Mercury" "Venus" "Earth" "Mars" "Jupiter" "Saturn" "Uranus" "Neptune"]
        idx1 (.indexOf planets planet1)
        idx2 (.indexOf planets planet2)]
    (if (or (= -1 idx1) (= -1 idx2))
      []
      (let [lower (min idx1 idx2)
            upper (max idx1 idx2)
            result (subvec planets (inc lower) upper)]
        (if (= (count result) 1)
          (first result)
          result)))))