(defn map-keys-deep
  "Recursively transform all map keys with function `f`, preserving vector and set contents.
  Non-collection values are returned unchanged.
  >>> (map-keys-deep keyword {\"a\" 1, \"b\" {\"c\" 2}, :d [ {\"e\" 3} ]})
  {:a 1, :b {:c 2}, :d [{:e 3}]}
  >>> (map-keys-deep name {:a 1, :b #{ {:c 2} }})
  {\"a\" 1, \"b\" #{ {\"c\" 2} }}
  "
  [f x]
  (cond
    (map? x) (into (empty x) (map (fn [[k v]] [(f k) (map-keys-deep f v)]) x))
    (vector? x) (vec (map #(map-keys-deep f %) x))
    (set? x) (set (map #(map-keys-deep f %) x))
    (sequential? x) (doall (map #(map-keys-deep f %) x))
    :else x))