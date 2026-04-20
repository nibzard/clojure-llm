(defn cylinder-metrics
  "Given a radius r and height h, return a map with the cylinder's total surface area and volume.

  Use the formulas:
  :surface-area = 2πr(r + h)
  :volume = πr²h

  Examples:
  (cylinder-metrics 1 1) => {:surface-area 12.566370614359172, :volume 3.141592653589793}
  (cylinder-metrics nil 2) => nil"
  [r h]
  (when (and (some? r) (some? h))
    {:surface-area (* 2 Math/PI r (+ r h))
     :volume (* Math/PI r r h)}))