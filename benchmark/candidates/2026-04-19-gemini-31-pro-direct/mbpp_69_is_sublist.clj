(defn is_sublist [l s]
  (if (empty? s)
    true
    (boolean (some #(= (seq s) %) (partition (count s) 1 l)))))