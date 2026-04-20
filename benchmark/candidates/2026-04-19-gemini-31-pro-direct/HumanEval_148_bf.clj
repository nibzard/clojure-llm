(defn bf [planet1 planet2]
  (let [planets ["Mercury" "Venus" "Earth" "Mars" "Jupiter" "Saturn" "Uranus" "Neptune"]
        p-map (zipmap planets (range))
        idx1 (p-map planet1)
        idx2 (p-map planet2)]
    (if (and idx1 idx2)
      (let [start (inc (min idx1 idx2))
            end (max idx1 idx2)]
        (if (< start end)
          (vec (subvec planets start end))
          []))
      [])))