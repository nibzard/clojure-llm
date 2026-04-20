(defn triangle_area
  "	Write a cljthon function to find the area of the largest triangle that can be inscribed in a semicircle with a given radius."
  [r]
  (let [r (double (or r 0))]
    (if (neg? r)
      0.0
      (* r r))))