(defn surfacearea_cylinder
  "Return the total surface area of a cylinder.

  Accepts radius r and height h, where either argument may be:
  - a number
  - nil, which is treated as 0

  Returns the total surface area using the formula:
  2πr(r + h)

  Examples:
  (surfacearea_cylinder 3 5) => 150.79644737231007
  (surfacearea_cylinder nil 5) => 0.0
  (surfacearea_cylinder 2 nil) => 25.132741228718345"
  [r h])