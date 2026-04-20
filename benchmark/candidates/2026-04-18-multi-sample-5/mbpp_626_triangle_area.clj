(defn triangle_area
  "	Write a cljthon function to find the area of the largest triangle that can be inscribed in a semicircle with a given radius."
  [r]
  (when (some? r)
    (/ (* r r) 2.0)))