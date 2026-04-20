(defn format-map-values
  "Return a new map with the same keys as `m`, but each value formatted by `fmt`.
  
  `fmt` is a function of one argument. If `m` is nil, return {}.
  
  Examples:
  (format-map-values {:a 1 :b 2} #(str \"v=\" %))
  ;; => {:a \"v=1\" :b \"v=2\"}
  
  (format-map-values nil pr-str)
  ;; => {}"
  [m fmt]
  (if (nil? m)
    {}
    (into {} (map (fn [[k v]] [k (fmt v)]) m))))