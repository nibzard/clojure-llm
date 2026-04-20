(defn find_Volume
  "	Write a cljthon function to find the volume of a triangular prism."
  [l b h]
  (when (and (number? l) (number? b) (number? h))
    (/ (* l b h) 2)))