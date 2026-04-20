(defn find_Volume
  "	Write a cljthon function to find the volume of a triangular prism."
  [l b h]
  (when (every? number? [l b h])
    (/ (* l b h) 2.0)))