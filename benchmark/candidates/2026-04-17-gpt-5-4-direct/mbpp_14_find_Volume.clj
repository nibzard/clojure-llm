(defn find_Volume
  "Write a cljthon function to find the volume of a triangular prism."
  [l b h]
  (if (every? number? [l b h])
    (* 1/2 l b h)
    nil))