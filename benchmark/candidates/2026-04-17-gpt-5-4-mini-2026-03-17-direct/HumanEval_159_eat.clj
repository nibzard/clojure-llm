(defn eat
  [number need remaining]
  (let [eaten (min need remaining)]
    [(+ number eaten) (- remaining eaten)]))