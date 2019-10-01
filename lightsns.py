from mcp3008 import MCP3008
adc = MCP3008()
print( adc.read( channel = 0 ) )#if necessary perform several times
