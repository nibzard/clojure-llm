(defn bf
  [planet1 planet2]
  (let [planets ["Mercury" "Venus" "Earth" "Mars" "Jupiter" "Saturn" "Uranus" "Neptune"]
        i1 (.indexOf planets planet1)
        i2 (.indexOf planets planet2)]
    (if (or (neg? i1) (neg? i2))
      []
      (let [between (subvec (vec planets) (inc (min i1 i2)) (max i1 i2))]
        (if (= 1 (count between))
          (first between)
          between)))))