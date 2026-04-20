(defn is_nested
  [string]
  (let [depths (reductions
                (fn [depth ch]
                  (case ch
                    \[ (inc depth)
                    \] (dec depth)
                    depth))
                0
                string)]
    (boolean (some #(> % 1) depths))))