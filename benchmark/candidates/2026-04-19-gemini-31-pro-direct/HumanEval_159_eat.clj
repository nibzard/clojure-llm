(defn eat
  [number need remaining]
  (let [eaten-now (min need remaining)]
    [(+ number eaten-now) (- remaining eaten-now)]))