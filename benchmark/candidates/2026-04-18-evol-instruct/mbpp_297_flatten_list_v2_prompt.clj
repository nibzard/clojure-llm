(defn flatten-map
  "Return a flat map from a nested map structure by joining nested keys with dots.

  Examples:
  (flatten-map {:a {:b 1 :c {:d 2}}})
  => {\"a.b\" 1, \"a.c.d\" 2}

  (flatten-map nil)
  => {}"
  [m])