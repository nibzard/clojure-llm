(defn eulerian_triangle
  "Return the first n rows of the Eulerian triangle as a vector of vectors.

  The first row is [1]. Each next row k (0-based) contains the Eulerian numbers
  a(k, m) for m = 0..k.

  Examples:
  (eulerian_triangle 1) => [[1]]
  (eulerian_triangle 4) => [[1] [1 0] [1 1 0] [1 4 1 0]]"
  [n])